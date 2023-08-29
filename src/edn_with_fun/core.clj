(ns edn-with-fun.core
  (:require [edn-with-fun.logic.send-events :refer [send-events]]))

(defn -main [events]
  (send-events (read-string events)))
