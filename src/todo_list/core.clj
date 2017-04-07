(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
    [ring.middleware.reload :refer [wrap-reload]]))

(defn home
  "Application home page",
  [request]
  (if (= "/" (:uri request))
    {:status 200
     :body "<h2>Todo List Application</h2>"
     :headers {}}

     {:status 404
      :body "<h2>404 - Resource not found.</h2>"
      :headers {}}))

(defn -main
  "Web server."
  [port]
  (jetty/run-jetty home
    {:port (Integer. port)}))

(defn -dev-main
  "ring and jetty live reload"
  [port]
  (jetty/run-jetty (wrap-reload #'home)
  {:port (Integer. port)}))
