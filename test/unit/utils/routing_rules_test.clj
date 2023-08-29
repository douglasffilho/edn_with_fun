(ns unit.utils.routing-rules-test
  (:require [clojure.test :refer :all]
            [matcher-combinators.test :refer :all]
            [edn-with-fun.utils.routing-rules :refer [routing-rules]]))

(deftest replace-in-file-map-rules-by-functions
  (testing "Read edn file and replace functions references by real functions"
    (is (match? [{:rule-check        #'edn-with-fun.logic.rule-checks/is-testing
                  :topic-destination "testing_topic"}
                 {:rule-check        #'edn-with-fun.logic.rule-checks/is-analytics
                  :topic-destination "analytics_topic"}]
                routing-rules))))
