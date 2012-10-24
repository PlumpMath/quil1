(ns quil1.ex1
  (:use quil.core
        [quil.helpers.seqs :only [seq->stream range-incl]]))

(defn setup []
  (frame-rate 24)
  (smooth)
  (background 180)
  (stroke 0)
  (stroke-weight 5)
  (fill 255 25)
  (let [diams (range-incl 10 400 10)]
    (set-state! :diam (seq->stream diams)
                :cent-x (/ (width) 2)
                :cent-y (/ (height) 2))
(doseq [x diams]
    (println x))
    )
  
  )

(defn draw []
  (let [cent-x (state :cent-x)
        cent-y (state :cent-y)
        diam   ((state :diam))]
    (when diam
      (background 180)
      (ellipse cent-x cent-y diam diam))))

(defsketch gen-art-2
  :title "Growing circle"
  :setup setup
  :draw draw
  :size [500 300]
  :keep-on-top true)