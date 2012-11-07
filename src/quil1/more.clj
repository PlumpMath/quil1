(ns quil1.more
  (:use [quil.core]
        [quil1.util]
        [quil1.transformer])
  (:import (toxi.color TColor ColorRange))
)
(defn color-dark []
  (let [colorcito (. TColor newRandom)]
      ; (println (.toARGB colorcito))
       colorcito
       )
  )
(defn nuevo-toxi-color []
  (.toARGB (color-dark))
  )

;(defn inicializa-colores [atom-list limite]
 ; (dotimes [n limite]
 ;   (swap! atom-list conj (nuevo-toxi-color))
 ;   )
   ; )
  
(defn lafuncion [row-height]
  (fn [v y]
    (conj v {:height row-height
              :color (nuevo-toxi-color)
              :y (if (not= y 0)
                   (let [
                         vv (get v (- y 1))
                         yi (:y vv)
                         h (:height vv)]
                     (+ yi h))
                   0)})))

(defn init-rows
  [n applet-height]
  (let [row-height (/ applet-height n)
        mi-f (lafuncion row-height)]
    (reduce mi-f  [] (range n))
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

(defn change-row-color
  [id-row atom-rows]
   (let [unidades (final (range (count @atom-rows)) id-row)
    suma (reduce + unidades)
    val-ud (double (/ (height) suma))
        ]
     (doall  (map (fn [valor unidad id-rowi]
                    (swap! atom-rows edit-property id-rowi :height (double (* val-ud unidad)))
                     )
                 @atom-rows
                 unidades
                 (range (count @atom-rows))))
     )
  (let [calculo (calculo-altura-filas-en-relacion-existentes @atom-rows)]
    (doseq [row calculo]
      (let [y (row 0)
            height (row 1)
            id (row 2)]
       
        (swap! atom-rows edit-property id :y y)
        )
      )
    

    )
  
   )