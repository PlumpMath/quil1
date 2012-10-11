(ns quil1.util
  (:use [quil.core]))

(defn foo
  "funcion de prueba"
  [x]
  (println  "Hello, World nrepl!" x))
  

(defn mi-random
  [actual]
 (num (* (Math/random) 255)))

(defn randomiza
  "devuelve un numero aleatorio como limite 255"
  [ey]
  ;; (println (class (num 255 )))
  (mi-random ey))


(defn color-rgb-random
  "rgb random color"
  [ey]
  (let [valor  (vector (random 255) (random 255) (random 255))]
    (println valor)
   (apply color valor)))
(defn intenta [comando & args]
   (try (apply comando args)
        (catch ClassCastException e (prn (class Exception)))
         (finally 
          (println "Done."))
         ))