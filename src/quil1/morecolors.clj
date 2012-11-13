(ns quil1.morecolors
    (:use [quil.core]
        [quil1.util]
        [quil1.transformer :as transformer])
  (:import (toxi.color TColor ColorRange))
  )

(defn color-dark []
  (let [colorcito (. TColor newRandom)]
;       (println (.toARGB colorcito))
       colorcito
       )
  )

(defn nuevo-toxi-color []
  (.toARGB (color-dark))
  )