(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
    [ring.middleware.reload :refer [wrap-reload]]
    [compojure.core :refer [defroutes GET]]
    [compojure.route :refer [not-found]]
    [ring.handler.dump :refer [handle-dump]]))

(defn home
  "Application home page"
  [request]
    {:status 200
     :body "<h1>Todo List Application</h1>"
     :headers {}})

(defn contact
  "Contact page"
  [request]
  {:status 200
   :body "<h1>Contact info is comming soon...</h1>"
   :headers {}})


;;routes
(defroutes app
  (GET "/" [] home)
  (GET "/contact" [] contact)
  (GET "/debug-info" [] handle-dump)
  (not-found "<h2>404 - Resource not found!</h2>"))

;;main environment
(defn -main
  "Web server."
  [port]
  (jetty/run-jetty app
    {:port (Integer. port)}))
;;dev environment
(defn -dev-main
  "ring and jetty live reload"
  [port]
  (jetty/run-jetty (wrap-reload #'app)
  {:port (Integer. port)}))
