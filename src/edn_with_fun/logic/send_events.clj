(ns edn-with-fun.logic.send-events
  (:require [edn-with-fun.utils.routing-rules :refer [routing-rules]]))

(defn- send-event-to-topic [event]
  (fn [topic] (do
                (println "Sent event to" topic ":" event)
                event)))

(defn- send-event-to-topics [event topics]
  (mapv (send-event-to-topic event) topics))

(defn- mark-to-send [event]
  (fn [routing-rule] (assoc routing-rule :must-send ((:rule-check routing-rule) event))))

(defn- must-send? [routing-rule]
  (true? (:must-send routing-rule)))

(defn- event->flagged-routing-rules [event]
  (mapv (mark-to-send event) routing-rules))

(defn debug [value]
  (println value)
  value)

(defn- send-event [event]
  (->> (event->flagged-routing-rules event)
       (filterv must-send?)
       (mapv #(:topic-destination %))
       (send-event-to-topics event)))

(defn send-events [events]
  (mapv send-event events))
