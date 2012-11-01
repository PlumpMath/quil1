(ns quil1.ejemplo
  (:use [quil.core]
        [quil1.util]
        [mijson]
        [clj-time.local :only (local-now)]
        [quil.helpers.seqs :only [seq->stream range-incl]]
        )
(:import java.awt.event.KeyEvent (toxi.color TColor ColorRange))
)

(def rows-debug )

(def ancho 100)

(def mensaje (atom "Clojure Quil: Try to select a row!"))

(defn color-dark []
  (let [colorcito (. TColor newRandom)]
      ; (println (.toARGB colorcito))
       colorcito
       )
  
  )

(defn nuevo-toxi-color []
  (.toARGB (color-dark))
  )

(defn add-color-to-list
  [n]
   (swap! (state :colors) conj  (nuevo-toxi-color))
        )

(def color-text (atom 0))

(defn cross-rows
  [f]
(dotimes  [n (state :cubes)]
      (f n)
      )
  )

(defn hola [] "hola")

(defn inicializa-colores
  []
  (cross-rows add-color-to-list)
  )

(defn reinit-colors
  []
  (swap! (state :colors) (fn [x] []))
(inicializa-colores)
  )

(defn init-rows
  [n applet-height]
  (let [row-height (/ applet-height n)]
    (reduce (fn [v y] (conj v [y {:height row-height :color (nuevo-toxi-color)}])) {} (range n))
    )
 )

(defn calculo-altura-filas-en-relacion-existentes
  "calculo-altura-filas-en-relacion-existentes"
  [nmapa]
  (reduce (fn [array v]
            (let [[clave mapa] v
                  valor (last array)
                  valor-util (get valor 1 0)]
              (conj array [ valor-util
                           (+ (:height mapa)  valor-util )
                           clave
                           
                           ]
                    )))
          [] nmapa)
  )

(defn setup []
  (smooth)
  (background 100)

  (let [rows-count (count (parsea-ejemplo))]
  (set-state!
   :fuente (create-font "Zapfino" 15)
   :cubes rows-count
   :colors (atom [])
 
   :selected-row (atom 0)
   :rows(atom (init-rows rows-count (height)))
   )
)
  (inicializa-colores)
 
  (println "el contador " (count (deref (state :colors))))
  (println "el mapa"  (deref (state :rows)))
  (text-font (state :fuente) 15)
  
  )

(def iniciado (atom false))

(defn paint-random-circle [valor]
  ;(println (str valor "paint-random-circle!!"))
  (fill (random 255))
  (ellipse (num (random 0 500)) (num (random 0 500)) 50 50)
  )

(defn rowish
  [n f]
  (let [altura (/ (height)  (state :cubes))
        posicion (* altura n)]
        (f n posicion altura)
   )
  )

(defn paint-row
  [n]

  (rowish n (fn [n y-0 altura]
            

   (fill ((deref (state :colors))  n))
    
    
    (rect 0 y-0 (width) altura))
          )
  )

(defn draw []

  (background 100)
  (doseq [rr @(state :rows)]

    (let  [[clave mapa] rr
           colorito (:color mapa)
           altura (:height mapa)
           y-0 (* altura clave)
           
           ]
      (fill colorito)
      (rect 0 y-0 (width) altura )
;      (println clave)
      )
    
    )


;(println (state :cubes))
(fill 150)

;(println   (deref (state :rows)))
;(cross-rows paint-row)
 
  
;;  (rect ancho ancho 300 300)
  (stroke 255)
  
  (when-not @iniciado
    (do
      (println "no iniciado")
      (println (class @color-text))
      (println "size" (state :cubes)   (str ( width) " - " (height)))
      (swap! iniciado complement)
      ))
(fill @color-text)
;(println (state :mensaje))
   (text @mensaje 100 80)
  )

(defn paint-circles-reading-stats
  []
  (do
   ; (swap! (state :colors) [])
    (dorun (map paint-random-circle (parsea-ejemplo)))


  ;;  (dotimes   [n (count (parsea-ejemplo))]  (paint-random-circle n))
                                        ;(paint-random-circle)
   
    ;;  (println (str "la clae. " (class (apply color (color-rgb-random)))))

    (swap! iniciado complement)
    
    (swap! color-text  color-rgb-random)
    
    (let [colorito (color 100)]


      ;;        (println colorito)

      ))
  )

(defn change-row-color
  [id-row]
  (swap! (state :rows) (fn [the-map]
                      (let [[k m] (find the-map id-row)
                            nuevo-mapa (assoc m :color (nuevo-toxi-color)) 
                            ]
                        (assoc the-map id-row nuevo-mapa))))
  )

(defn log-rows []
  (println @(state :rows)) )

(defn mouse-clicked[]

  (println "click point :" (mouse-x) " " (mouse-y))

  (let [calculo (calculo-altura-filas-en-relacion-existentes @(state :rows))]

    
    (doseq [row calculo]
         (let [y (row 0)
                      height (row 1)
                      id (row 2)]
           (when (and (< y (mouse-y)) (> height (mouse-y)))
             (change-row-color id))
           (swap! mensaje (fn [_] "Congratulations, you did it!"))
;             (println "fila seleccionada " id))
           )
         )
    )
 ; (let [id-row  (rand-int (count @(state :rows)))]
  ;      (change-row-color id-row)    
  ;)
)

(defn cambia [ey]
  (nuevo-toxi-color)
  )

(defn key-pressed []
  (println (str "mi clase ---- "(class (color-rgb-random nil))))

  (println (str "la otra" (class (.toARGB (color-dark)))))
  (swap! color-text cambia)
 ; (text-font (state :fuente) (random 50 200))
  ;;(println (str "raw-key" (raw-key)))
  (println (key-as-keyword))
;;  (try (print  (key-as-keyword) " key: ")
  ;;     (catch Exception e (println (.getMessage e)))
 ;;      )
                       

  )