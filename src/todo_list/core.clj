(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]))

(defn home
  "Application home page",
  [request]
  {:status 200
   :body "<h2>Todo List App</h2>"
   :headers {}})

(defn -main
  "Web server."
  [port]
  (jetty/run-jetty home
    {:port (Integer. port)}))
