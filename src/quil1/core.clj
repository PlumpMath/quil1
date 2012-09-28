;;https://github.com/quil/quil/wiki/Dynamic-Workflow-(for-generic-editors)
(ns quil1.core
  (:use quil.core)
  (:require [quil1.ejemplo :as ejemplo]))

(defsketch example                
  :title "First Dynamic Example with Clojure/Quil=Clojure on top of Processing.org"
  :setup ejemplo/setup           
  :draw ejemplo/draw              
  :size [523 500])                

