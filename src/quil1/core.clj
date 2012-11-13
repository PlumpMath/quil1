;;https://github.com/quil/quil/wiki/Dynamic-Workflow-(for-generic-editors)
(ns quil1.core
  (:use [quil.core]  [clj-time.local :only (local-now)])
  (:require [quil1.grid :as grid]))

(defsketch example                
  :title "mi sketch: learning clojure"
  :setup grid/setup           
  :draw grid/draw
  :mouse-clicked grid/mouse-clicked
  :key-pressed grid/key-pressed
   :size [200 500])                
