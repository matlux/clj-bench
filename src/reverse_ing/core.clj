(ns reverse-ing.core
  (:require
   [taoensso.timbre :as timbre]) )

(timbre/refer-timbre)


(defn foo
  "I don't do a whole lot."
  [x]
  (inc x))


(defn f-glob [a]
  (foo a))

(defn f-loc [a]
  (let [f (fn [x] (inc x))]
    (f a)))

(def t 1000000000)

(defn bench1 []
  (let [loc (fn [x] (inc x))]
   (time (dotimes [_ t] (foo 0)))
   #_(dotimes [_ 2]
     (println "glob*")
     (time (dorun (take t (iterate foo 0))))
     (println "loc*")
     (time (dorun (take t (iterate loc  0)))))
   (dotimes [_ 2] (println "glob")
            (time (dotimes [_ t] (foo 0)))
            (println "loc")
            (time (dotimes [_ t] (loc  0))))
   (dotimes [_ 2] (println "glob")
            (time (dotimes [_ t] (f-glob 0)))
            (println "loc")
            (time (dotimes [_ t] (f-loc  0))))))

(defn bench2 []
  (let [loc (fn [x] (inc x))]
   (time (dotimes [_ t] (foo 0)))
   (dotimes [_ 2] (println "glob")
    (time (dotimes [_ t] (p :glob (f-glob 0))))
    (println "loc")
    (time (dotimes [_ t] (p :loc (f-loc  0)))))))

(defn bench3 []
  (let [loc (fn [x] (inc x))]
   (time (dotimes [_ t] (foo 0)))

   (p :glob
      (dotimes [_ 4]
        (println "glob")
        (dotimes [_ t] (foo 0))))
   (p :loc
      (dotimes [_ 4]
        (println "loc")
        (dotimes [_ t] (loc  0))))))

(defn bench4 []
  (let [loc (fn [x] (inc x))]
   (time (dotimes [_ t] (foo 0)))

   (p :glob
      (dotimes [_ 2]
        (println "glob")
        (dotimes [_ t] (f-glob 0))))
   (p :loc
      (dotimes [_ 2]
        (println "loc")
        (dotimes [_ t] (f-loc  0))))))

(defn -main []
  (profile :info :Arithmetic (bench3))
  ;;(profile :info :Arithmetic (bench4))
  )
