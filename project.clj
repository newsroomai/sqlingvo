(defproject ai.nws/sqlingvo "0.9.36-SNAPSHOT"
  :description "A Clojure DSL to create SQL statements"
  :url "http://github.com/newsroomai/sqlingvo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.5.2"
  :deploy-repositories [["releases" :clojars]]
  :dependencies [[noencore "0.3.7"]
                 [org.clojure/clojure "1.10.1"]]
  :plugins [[jonase/eastwood "0.3.11"]
            [lein-cljsbuild "1.1.8"]
            [lein-difftest "2.0.0"]
            [lein-doo "0.1.11"]]
  :eastwood {:config-files ["test-resources/eastwood.clj"]
             :exclude-linters [:local-shadows-var]}
  :profiles
  {:dev
   {:dependencies [[org.clojure/core.rrb-vector "0.1.2"]
                   [org.clojure/test.check "1.1.0"]]}
   :provided
   {:dependencies [[org.clojure/clojurescript "1.10.597"]]}
   :repl
   {:dependencies [[com.cemerick/piggieback "0.2.2"]
                   [reloaded.repl "0.2.4"]]
    :plugins [[figwheel-sidecar "0.5.20"]]
    :init-ns user
    :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
  :aliases
  {"ci" ["do"
         ["clean"]
         ["difftest"]
         ["doo" "node" "node" "once"]
         ["doo" "phantom" "none" "once"]
         ["doo" "phantom" "advanced" "once"]
         ["lint"]]
   "lint" ["do"  ["eastwood"]]}
  :cljsbuild
  {:builds
   [{:id "none"
     :compiler
     {:main sqlingvo.test.runner
      :optimizations :none
      :output-dir "target/none"
      :output-to "target/none.js"
      :parallel-build true
      :pretty-print true
      :verbose false}
     :source-paths ["src" "test"]}
    {:id "node"
     :compiler
     {:main sqlingvo.test.runner
      :optimizations :none
      :output-dir "target/node"
      :output-to "target/node.js"
      :parallel-build true
      :pretty-print true
      :target :nodejs
      :verbose false}
     :source-paths ["src" "test"]}
    {:id "advanced"
     :compiler
     {:main sqlingvo.test.runner
      :optimizations :advanced
      :output-dir "target/advanced"
      :output-to "target/advanced.js"
      :parallel-build true
      :pretty-print true
      :verbose false}
     :source-paths ["src" "test"]}]})
