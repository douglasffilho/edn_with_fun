(ns edn-with-fun.logic.send-events
  (:require [edn-with-fun.utils.routing-rules :refer [routing-rules]]))

(defn- send-event-to-topic [event]
  (fn [topic] (println "Sent event to" topic ":" event)))

(defn- send-event-to-topics [event topics]
  (mapv (send-event-to-topic event) topics))

(defn- must-send [event]
  (fn [routing-rule] (assoc routing-rule :must-send ((:rule-check routing-rule) event))))

(defn- must-send? [routing-rule]
  (true? (:must-send routing-rule)))

(defn- send-event [event]
  (->> (mapv (must-send event) routing-rules)
       (filter must-send?)
       (mapv #(:topic-destination %))
       (send-event-to-topics event)))

(defn send-events [events]
  (mapv send-event events))
