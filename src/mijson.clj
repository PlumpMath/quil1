(ns mijson
  (:use [clojure.java.io] [midje.sweet])
  (:require [clj-http.client :as client] [cheshire.core :as json])
  )

(def miurl "http://elections.huffingtonpost.com/pollster/api/polls.json?page=2")

(defn parsea-ejemplo []
  (json/decode (:body (client/get miurl)) true) )

(defn first-keys []
  (keys  (first (parsea-ejemplo))))

(defn class-name-string [clase]
  (.getName (class clase)))

(defn tipo-valor-seq
  [clase]
  (if (= clase (class []))
    true
    false))

    

(defn walk-map
  [dati]
  (let [datos dati]
   (let [mapa-clase-valores   (map #(hash-map  :la-clave % :la-clase  (class (datos %)) ) (keys datos))]
     ;mapa-clase-valores
    (doseq [x mapa-clase-valores]
              (let [{clave :la-clave clase :la-clase} x]
                (if (tipo-valor-seq clase)
                  (doseq [x (datos clave)]
                  (walk-map x))
                  (println (str  clave " > " (datos clave))))
              
                ))
  ;  (doseq [[clave clase] mapa-clase-valores ]
 
     ;  (str clave clase)
     ;  )
   )
   )
  )
(defn walk-map-key-class
  []
 (walk-map (first-json))
  )


(defn first-json []
  (first (parsea-ejemplo)))

(defn dame-valor-ejemplo
  "probar con :method"
  [clave]
  ((first (parsea-ejemplo)) clave))

(defn obten-valor [x]
        (x "method"))


(defn genera-obten-valor [clave]
  (fn [x] (x clave)))

(def metodolea (genera-obten-valor :method))

(defn pinta-ejemplo []
  (map (genera-obten-valor :sponsors) (parsea-ejemplo))
  )

(fact (dame-valor-ejemplo :method)=>"apache")