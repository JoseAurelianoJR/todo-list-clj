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

(defn say-hello
  "Say hello page"
  [request]
  (let [name (get-in request [:route-params :name])]
  {:status 200
   :body (str "Hello " name "! <br/> Welcome for todo list app.")
   :headers {}}))

(defn bhaskara
  "Bhaskara calculator page"
  [request]
  (let [a (Double. (get-in request [:route-params :a]))
       b (Double. (get-in request [:route-params :b]))
       c (Double. (get-in request [:route-params :c]))
       delta (Double. (- (* b b) (* 4 a c)))
       x1 (Double. (/ (+ (- b) (Math/sqrt delta)) (* 2 a)))
       x2 (Double. (/ (- (- b) (Math/sqrt delta)) (* 2 a)))]
  {:status 200
   :body (str "delta = " delta " x1 = " x1 " x2 = " x2)
   :headers {}
  }))


;;routes
(defroutes app
  (GET "/" [] home)
  (GET "/contact" [] contact)
  (GET "/debug-info" [] handle-dump)
  (GET "/say-hello/:name" [] say-hello)
  (GET "/bhaskara/:a/:b/:c" [] bhaskara)
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
