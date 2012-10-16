(ns mijson
  (:use [clojure.java.io] [midje.sweet])
  (:require [clj-http.client :as client] [cheshire.core :as json])
  )

(def miurl "http://elections.huffingtonpost.com/pollster/api/polls.json?page=2")

(defn parsea-ejemplo []
  (json/parse-string (:body (client/get miurl))))

(defn dame-valor-ejemplo
  "probar con 'method'"
  [clave]
  ((first (parsea-ejemplo)) clave))


(fact (dame-valor-ejemplo "method")=>"apache")