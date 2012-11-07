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
  [nmapa]
  (reduce (fn [array mapa]
            (let [
                  valor (last array)
                  valor-util (get valor 1 0)
                  id (count array)]
              (conj array [ valor-util
                           (+ (:height mapa)  valor-util )
                           id
                           
                           ]
                    )))
          [] nmapa)
  )
(defn ejemplop [the-map id-row new-height]
        (let [m (get the-map id-row)
              nuevo-mapa (assoc m :height new-height)]
                   (assoc the-map id-row nuevo-mapa)))

(defn change-row-color
  [id-row atom-rows]
  
  (let [unidades (final (range (count @atom-rows)) id-row)
    suma (reduce + unidades)
    val-ud (double (/ (height) suma))
        ]
    (println (height) "valor unidad " val-ud "id-row " id-row "suma" suma "unidades " unidades)
    (doall  (map (fn [valor unidad ident]
                   (let [
                         vv (+ 1 1)]
;                     (println "id: "indent "val-ud: "val-ud "ud" unidad "tota" (double (* val-ud unidad)))
                     (swap! atom-rows ejemplop ident (double (* val-ud unidad)))
                     ))
                 @atom-rows
                 unidades
                 (range (count @atom-rows))))
  )
  )