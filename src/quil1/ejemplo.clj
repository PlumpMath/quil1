(ns quil1.ejemplo
  (:use [quil.core]
        [quil1.util]
        [clj-time.local :only (local-now)])
(:import java.awt.event.KeyEvent)
)
(def ancho 100)


(def color-text (atom 0))
(defn setup []
  (smooth)
  (background 100)

  (set-state! :fuente (create-font "Zapfino" 12))
 
  (text-font (state :fuente) 12)
  
  )

(def iniciado (atom false))
(defn draw []


  (fill 150)
;;  (rect ancho ancho 300 300)
  (stroke 255)
  (line 0 140 100 100)
  (when-not @iniciado
    (do
      (println "no iniciado")
 (println (class @color-text))
      (swap! iniciado complement)
      ))
  (fill @color-text)
   (text "eyyy" 30 40)
  )

(defn mouse-clicked[]
  (do
    (print "click! ")
  ;;  (println (str "la clae. " (class (apply color (color-rgb-random)))))

    (swap! iniciado complement)
    
    (swap! color-text  color-rgb-random)
    
    (let [colorito (color 100)]


;;        (println colorito)

      ))
      )


(defn key-pressed []
  (text-font (state :fuente) (random 50 200))
  ;;(println (str "eeeeeeeeeeeeee " (raw-key)))
  (println (key-as-keyword))
;;  (try (print  (key-as-keyword) " key: ")
  ;;     (catch Exception e (println (.getMessage e)))
 ;;      )
                       

  )









