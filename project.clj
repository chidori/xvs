(defproject xvs "0.1.0-SNAPSHOT"
  :description "XML validation server"
  :url "http://github.com/chidori/xvs"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [http-kit "2.1.16"]
                 [compojure "1.1.6"]
                 [ring-server "0.3.0"]
                 [selmer "0.6.5"]
                 [xerces/xercesImpl "2.11.0"]
                 [org.eclipse.wst.xml.xpath2.processor "1.1.0"]
                 [org.clojure/data.json "0.2.4"]]
  :aot :all
  :main xvs.core)
