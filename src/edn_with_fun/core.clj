(ns edn-with-fun.core
  (:require [edn-with-fun.logic.send-events :refer [send-events]]))

(def events [{:name "testing_v1"
              :test true}
             {:name "not_send_v1"}
             {:name "not_testing_v1"
              :test false}
             {:name "button_clicked_v1"
              :analytics true}])

(defn -main []
  (send-events events))
