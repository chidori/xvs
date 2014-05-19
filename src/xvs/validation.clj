(ns xvs.validation
  (:import [java.io File StringReader]
           [javax.xml.validation Schema SchemaFactory Validator]
           [javax.xml.transform.stream StreamSource]
           [org.xml.sax SAXException]))

(def schema-v11 "http://www.w3.org/XML/XMLSchema/v1.1")

(defn get-schema-validator [schema]
  "Create schema validator"
  (try
    (let [schema (if (nil? schema) (str "") schema)
          validator (-> (SchemaFactory/newInstance schema-v11)
                        (.newSchema (StreamSource. (StringReader. schema)))
                        (.newValidator))]
      {:status "ok" :message nil :validator validator})
    (catch SAXException e
      {:status "schema-error" :message (str (.getMessage e)) :validator nil})))