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

 (defn path-jerarquico [numero]
   (apply str (repeat numero "->") ))

(defn pinta-json [data contador]
  (doseq [keyval data]
    (let [clave (key keyval) valor (val keyval)]
      (if (= (class valor) (class []))
        (do (prn (path-jerarquico contador) "V" clave)
            (doseq [hijo valor]
              (pinta-json hijo (inc contador))
              ))
        (prn (path-jerarquico contador) "S" clave valor)))))


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
(defn first-json []
  (first (parsea-ejemplo)))

(defn walk-map-key-class
  []
 (walk-map (first-json))
  )



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

(defn pinta-primer-json []
  (pinta-json (first-json) 1)
  )



;(fact (dame-valor-ejemplo :method)=>"apache")