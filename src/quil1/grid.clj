(ns quil1.grid
  (:use [quil.core]
        [quil1.more]))

(defn setup []
  (background 100)
  (smooth)
  (size 500 500)
  ; rows-count == (count (parsea-ejemplo))
  (let [rows-count 10]
    (set-state!
     :fuente (create-font "Zapfino" 15)
     :rows(atom (init-rows rows-count (height)))
     )

    )

 

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
(def selected-row (atom nil))
(defn change-row []
       (change-row-color @selected-row (state :rows)))     

(defn mouse-clicked []
  (println "mouse!")
 (let [calculo (calculo-altura-filas-en-relacion-existentes @(state :rows))]

  
    (doseq [row calculo]
         (let [y (row 0)
                      height (row 1)
               id (row 2)]
  
           (when (and (< y (mouse-y)) (> height (mouse-y)))
             (reset! selected-row id)
             (change-row))
           

           )
         )
    )
  
 )

(defn avanza []
  (when-not (nil? @selected-row)
    (when (>  (count @(state :rows)) @selected-row)
      (swap! selected-row inc)
     (change-row)
      )
    )
    (when (nil? @selected-row)
      
    (reset! selected-row 0)
    (change-row)
    )
    )
(defn retrocede []
  (when-not (nil? @selected-row)
    (when (>   @selected-row 0)
      (swap! selected-row dec)
     (change-row)
      )
    )
    (when (nil? @selected-row)
      
    (reset! selected-row (count @(state :rows)))
    (change-row)
    )
    )


(defn key-pressed []

   
   (when (= (key-as-keyword) :down)
     
     (avanza))
   
   (when (= (key-as-keyword) :up)
    
     (retrocede))
   )


