(ns quil1.grid
  (:use [quil.core]
        [quil1.more]))

(defn setup []
  (background 100)
  (smooth)
  ; rows-count == (count (parsea-ejemplo))
  (let [rows-count 10]
    (set-state!
     :fuente (create-font "Zapfino" 15)
    ; :colors (atom [])
     :selected-row (atom 0)
     :rows(atom (init-rows rows-count (height)))
     )
   ; (inicializa-colores (state :colors) rows-count)
    )

 
 ; (println "el contador " (count (deref (state :colors))))
  (println "el mapa"  (deref (state :rows)))
  (text-font (state :fuente) 15)

  )

(defn draw []
 (background 100)
  (doseq [mapa @(state :rows)]
    (let  [colorito (:color mapa)
           altura (:height mapa)
           y-0 (:y mapa)]
      (fill colorito)
      (rect 0 y-0 (width) altura )
      )
    )
  )

(defn mouse-clicked []
(println "mouse!")
  )