;;https://github.com/quil/quil/wiki/Dynamic-Workflow-(for-generic-editors)
(ns quil1.core
  (:use [quil.core]  [clj-time.local :only (local-now)])
  (:require [quil1.ejemplo :as ejemplo]))

(defsketch example                
  :title "mi sketch: learning clojure"
  :setup ejemplo/setup           
  :draw ejemplo/draw
  :mouse-clicked ejemplo/mouse-clicked
  :key-pressed ejemplo/key-pressed
  :size [523 500])                
