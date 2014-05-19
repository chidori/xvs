(ns xvs.repl
  (:require [org.httpkit.server :as server]
            [xvs.core :as core]))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)
    (prn "server stopped")))

(defn start-server []
  (stop-server)
  (reset! server (server/run-server #'core/app {:port 9090}))
  (prn "server started"))

(defn restart []
  (stop-server)
  (start-server))
