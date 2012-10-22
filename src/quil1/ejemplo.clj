(ns quil1.ejemplo
  (:use [quil.core]
        [quil1.util]
        [mijson]
        [clj-time.local :only (local-now)])
(:import java.awt.event.KeyEvent (toxi.color TColor ColorRange))
)
(def ancho 100)
(defn nuevo-toxi-color []
  (.toARGB (color-dark))
  )
(defn color-dark []
  (let [colorcito (. TColor newRandom)]
       (println (.toARGB colorcito))
       colorcito
       )
  
  )

(def color-text (atom 0))
(defn setup []
  (smooth)
  (background 100)

  
  (set-state!
   :fuente (create-font "Zapfino" 12)
   :cubes (count (parsea-ejemplo))
   )
 
  (text-font (state :fuente) 12)
  
  )

(def iniciado (atom false))
(defn paint-random-circle [valor]
  (println (str valor "paint-random-circle!!"))
  (fill (random 255))
  (ellipse (num (random 0 500)) (num (random 0 500)) 50 50)
  )
(defn paint-row
  [n]
  (let [altura (/ (height)  (state :cubes))
            posicion (* altura n)
            ]
            (rect 0 posicion 100 altura))
  )
(defn draw []
  
(background 100) 

;(println (state :cubes))
  (fill 150)
  (dotimes  [n (state :cubes)]
      (paint-row n)
      )
 
  
;;  (rect ancho ancho 300 300)
  (stroke 255)
  (line 0 140 100 100)
  (when-not @iniciado
    (do
      (println "no iniciado")
      (println (class @color-text))
      (println "size" (state :cubes)   (str ( width) " - " (height)))
      (swap! iniciado complement)
      ))
  (fill @color-text)
   (text "eyyy" 30 40)
  )

(defn mouse-clicked[]
  (do
    (dorun (map paint-random-circle (parsea-ejemplo)))


  ;;  (dotimes   [n (count (parsea-ejemplo))]  (paint-random-circle n))
                                        ;(paint-random-circle)
    (print "click! ")
    ;;  (println (str "la clae. " (class (apply color (color-rgb-random)))))

    (swap! iniciado complement)
    
    (swap! color-text  color-rgb-random)
    
    (let [colorito (color 100)]


      ;;        (println colorito)

      ))
      )


(defn cambia [ey]
  (nuevo-toxi-color)
  )


(defn key-pressed []
  (println (str "mi clase ---- "(class (color-rgb-random nil))))

  (println (str "la otra" (class (.toARGB (color-dark)))))
  (swap! color-text cambia)
  (text-font (state :fuente) (random 50 200))
  ;;(println (str "raw-key" (raw-key)))
  (println (key-as-keyword))
;;  (try (print  (key-as-keyword) " key: ")
  ;;     (catch Exception e (println (.getMessage e)))
 ;;      )
                       

  )









