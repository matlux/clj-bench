(defproject reverse-ing "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.taoensso/timbre "3.2.1"]]
  :aot :all
  :main reverse-ing.core
  :jvm-opts ^:replace ["-verbose:gc" "-XX:+DoEscapeAnalysis"])
