(ns quil1.dimension)
(def b "welcome")

(defn selection-pass-half
 "is the selection of the list equal or greater than the middle of the list?"
  [liste selection]
  (let [mitad (/ (count liste) 2)]
    (if (>= selection mitad) true false))
  )

(defn last-element-not-nil [col]
  (if-let [l (last col)] l 1))

(def listado [:a :b :c :d :e :f :g :h :i :j] )
(def seleccion 5)

(defn duplicate-if-not-nil-function [val]
  (if (= val nil)
    ( * 1 1)
    ( * 2 val)
    )
  )

(defn create-incremental-sequence [listado ]
  (reduce (fn [lis val ] (conj lis (duplicate-if-not-nil-function (last lis)))) []  listado) )

(defn parte-lista [lista seleccion]
  (split-at (inc seleccion) lista))

(def demo   (create-incremental-sequence listado))


(def demo-partido  (parte-lista demo seleccion))

(def mio (into [] (demo-partido 0)))

(def mio2 (pop mio))

(defn continua-listado-decreciente []
  (reduce (fn [x y] (conj x (peek mio2))) [] (range seleccion (dec (count listado)))))

(defn finaliza []
  (into mio (continua-listado-decreciente))
  )