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