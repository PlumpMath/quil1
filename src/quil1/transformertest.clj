(ns quil1.transformertest
  (:use [midje.sweet] [quil1.transformer]))
(def mi-coleccion [1 2 3 4 5 6 7])
(def mi-seleccion 4)
(fact (distancia 1 3) => 2)

(fact (transforma mi-coleccion mi-seleccion) => '(4 3 2 1 0 1 2))

(def transformada (transforma mi-coleccion mi-seleccion))

(fact (maximo-valor transformada) => 4)

(fact (mi-fibo 4) => '(1 2 4 8 16))

(fact (final mi-coleccion mi-seleccion) => [1 2 4 8 16 8 4])