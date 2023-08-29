(ns unit.logic.rule-checks-test
  (:require [clojure.test :refer :all]
            [edn-with-fun.logic.rule-checks :refer :all]))

(deftest is-testing-event
  (testing "The event is a testing one"
    (is (is-testing {:name "bla_v0", :test true}))))

(deftest is-analytics-event
  (testing "The event is an analytics one"
    (is (is-analytics {:name "bla_v1", :analytics true}))))
