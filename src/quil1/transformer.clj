(ns quil1.transformer
  (:use [midje.sweet]))
(def m "welcome")



(defn distancia [posicion seleccion]
        (if (> seleccion posicion) 
            (- seleccion posicion)
          (- posicion seleccion))
        )


(defn transforma [coleccion seleccion]
  (map (fn [x](distancia x seleccion))  (range (count coleccion))))

(defn maximo-valor [coleccion]
(reduce (fn [x y] (if (> x y) x y)) 0 coleccion)
  )

(defn mi-fibo [maximo]
  (reduce (fn [list x] (conj list (* 2 (last list)))) [1]  (range maximo)))

(defn final [coleccion seleccion]
  (let [transformada (transforma coleccion seleccion)
        maxim  (maximo-valor transformada)
        fibos (into [] (reverse (mi-fibo maxim)))]
    (map (fn [x] (get fibos x) ) transformada)
    )
)