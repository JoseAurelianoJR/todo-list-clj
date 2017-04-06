(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]))

(defn -main
  "Web server."
  [port]
  (fn [request] {:status 200
                 :body "<p>Test app</p>"
                 :headers {}})
  {:port (Integer. port)})
