(ns edn-with-fun.utils.routing-rules)

(require 'edn-with-fun.logic.rule-checks)

(defn- resolve-rule [rule-as-string]
  (resolve (symbol rule-as-string)))

(defn- process-rule-check [rule-check]
  (update rule-check :rule-check resolve-rule))

(defn- process-rules-checks [source-map]
  (mapv process-rule-check source-map))

(def routing-rules (->> (slurp "resources/routing_rules.edn")
                        (read-string)
                        (process-rules-checks)))
