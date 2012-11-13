(ns quil1.more
  (:use [quil.core]
        [quil1.util]
        [quil1.morecolors :as mcolors]
        [quil1.transformer :as transformer])

  )


  
(defn init-row-height [row-height]
  (fn [array-col index-range]
    (conj array-col {:id (count array-col)  :height row-height  :color (mcolors/nuevo-toxi-color)
                     :y (if  (not=  index-range 0)
                  (let [last-element (get array-col (- index-range 1))
                        last-element-y (:y last-element)
                        h (:height last-element)]
                    (+ last-element-y h))
                  0)})))

(defn init-rows
  [n applet-height]
  (let [row-height (/ applet-height n) ]
    (reduce  (init-row-height row-height) [] (range n))
    )
  )



(defn calculo-altura-filas-en-relacion-existentes
  "calculo-altura-filas-en-relacion-existentes"
  [rows]
  (reduce (fn [array mapa]
            (let [
                  valor-util (if (last array) ( (last array) 1 ) 0)
                  id (count array)]
              (conj array [valor-util
                           (+ (:height mapa)  valor-util )
                           id]
                    )))
          [] rows)
  )
(defn edit-property [the-rows id-row the-property new-value]
  (let [m (get the-rows id-row)
        new-map (assoc m the-property new-value)]
    (assoc the-rows id-row new-map)))

(defn change-row-final
  "con el id-row seleccionado cambia la altura de las filas en relacion al algoritmo mi-fibo
 y despues cambia la posicion  de cada fila"
  [id-row atom-rows]
  (let [unidades (transformer/final (range (count @atom-rows)) id-row)
        suma (reduce + unidades)
        val-ud (double (/ (height) suma))
        ]
                                        ; cambia la altura de las filas
    (doall  (map (fn [valor unidad id-rowi]
                   (swap! atom-rows edit-property id-rowi :height (* val-ud unidad))
                   )
                 @atom-rows
                 unidades
                 (range (count @atom-rows))))
    )
  (let [calculo (calculo-altura-filas-en-relacion-existentes @atom-rows)]
                                        ; cambia la posicion y de las filas una vez recalculada la altura
    (doseq [row calculo]
      (let [y (row 0)
            height (row 1)
            id (row 2)]
        (swap! atom-rows edit-property id :y y)
        )
      ) )
  )