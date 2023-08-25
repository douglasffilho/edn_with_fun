(ns edn-with-fun.logic.rule-checks)

(defn is-testing [event]
  (true? (:test event)))

(defn is-analytics [event]
  (true? (:analytics event)))
