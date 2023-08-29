(ns unit.logic.send-events-test
  (:require [clojure.test :refer :all]
            [matcher-combinators.test :refer :all]
            [edn-with-fun.logic.send-events :refer [send-events]]))

(def events [{:name "testing_v1"
              :test true},
             {:name "not_send_v1"},
             {:name "not_testing_v1"
              :test false},
             {:name      "button_clicked_v1"
              :analytics true}])

(deftest send-only-rule-matching-events
  (testing "Given a list of events, only sends the valid ones"
    (is (match?
         [[{:name "testing_v1", :test true}] [] [] [{:name "button_clicked_v1", :analytics true}]]
         (send-events events)))))
