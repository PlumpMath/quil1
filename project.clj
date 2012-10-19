(defproject quil1 "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [
                  [org.clojure/clojure-contrib "1.2.0"] 
                 [org.clojure/clojure "1.4.0"]
                 [quil "1.6.0"][clj-time "0.4.4"]
                 [org.clojure/data.json "0.2.0"]
                 [com.cemerick/url "0.0.7"]
                 [lein-swank "1.4.4"]
                 [clj-http "0.5.5"]
                 [cheshire "4.0.3"]
                 [midje "1.4.0"]
;;                 [toxiclibs/core "1"]
  ;;               [toxiclibs/colorutils "1"]
                 ]
  :dev-dependencies [[midje "1.4.0"]]
    :profiles {:dev {:plugins [[lein-midje "2.0.0-SNAPSHOT"]]}} ;; Leiningen 2
    :jvm-opts ["-Xmx768M"]
;;    :repositories {"local" "/home/ubuntu-1204/.m2/repository/"}
   ;; :plugins [[lein-localrepo "0.4.1"]]
    ;  :repositories { "localShared" "file:///home/ubuntu-1204/.m2/repository"}
:repositories {"local" ~(str (.toURI (java.io.File. "maven_repository")))}

    )


