(ns xvs.core

  (:require [compojure.core :refer [defroutes routes GET POST]]
            [compojure.route :as route]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [org.httpkit.server :as server]
            [selmer.parser :refer [render-file cache-off!]]
            [xvs.validation :refer [get-schema-validator]]
            [clojure.data.json :as json])

  (:import [java.io StringReader]
           [javax.xml.transform.stream StreamSource]
           [javax.xml.validation Validator]
           [org.xml.sax SAXException])

  (:gen-class))

(selmer.parser/cache-off!)

(defn index []
  (render-file "templates/index.html" {}))

(defn validate [xml schema request]
  (let [{:keys [status message validator]} (get-schema-validator schema)]
    (if (nil? validator)
      (json/write-str {:status status :message message})
      (try
        (.validate validator (StreamSource. (StringReader. (if (nil? xml) (str "") xml))))
        (json/write-str {:status "ok" :message nil})
        (catch SAXException e
          (json/write-str {:status "xml-error" :message (.getMessage e)}))))))

(defroutes app-routes
  (route/resources "/")
  (GET "/" [] (index))
  (POST "/validate" [xml schema :as request] (validate xml schema request))
  (route/not-found "Page not found"))

(def app
  (-> (routes app-routes)
      wrap-keyword-params
      wrap-params))

(defn -main [& args]
  (println "Starting server on http://localhost:9090")
  (server/run-server #'app {:port 9090})
  (println "Started"))