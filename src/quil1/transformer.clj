(ns quil1.transformer)

(println "todo: cambiar por funcion de valor absoluto")
(defn distancia-a-seleccion [posicion seleccion]
        (if (> seleccion posicion) 
            (- seleccion posicion)
          (- posicion seleccion))
        )


(defn transforma-seleccion-a-fibo [coleccion seleccion]
  "partiendo de una coleccion y su elemento seleccionado dev"
  (map (fn [x](distancia-a-seleccion x seleccion))  (range (count coleccion))))

(defn maximo-valor [coleccion]
(reduce (fn [x y] (if (> x y) x y)) 0 coleccion)
  )

(defn mi-fibo [maximo]
  
  (reduce (fn [list x]  (conj list (* 2 (last list)))) [1]  (range maximo)))

(defn final [coleccion seleccion]
  ""
  (let [nueva-col (transforma-seleccion-a-fibo  coleccion seleccion)
        maxim  (maximo-valor nueva-col)
        fibos (into [] (reverse (mi-fibo maxim)))]
    (map (fn [x] (get fibos x) ) nueva-col)
    )
)