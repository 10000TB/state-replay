# state-replay

[![Build Status](https://travis-ci.org/10000TB/state-replay.svg?branch=master)](https://travis-ci.org/10000TB/state-replay) [![CircleCI](https://circleci.com/gh/10000TB/state-replay/tree/master.svg?style=svg)](https://circleci.com/gh/10000TB/state-replay/tree/master) [![Gitter chat](https://badges.gitter.im/state-replay/gitter.png)](https://gitter.im/state-replay/gitter)
  
  
  
![statereplay illustration](https://github.com/10000TB/state-replay/blob/master/src/main/resources/imgs/State-replay.png?raw=true)  
  
  
  
  

state-replay(<strong>WIP</strong>) helps you replay pipeline from predefined checkpoint(s).
  
  
  
[Install](#install)  
[Examples](#examples)  
[Current Development](#dev)  
[How to Contribute](#contribute)  
[Why do we need this plugin?](#motivation)  
[Liscense](#liscense)  

## Dev
- [ ] The `replay` will be first implemented in a simply way (For each `statepoint`, save variables name and values before, replay will reconstruct a temp workflow execution context, and workflow pipeline will start from there with those finished variables and values from last execution).
- [ ] `steps` in workflow pipeline are parsed and run one by one in single thread(multiple threads for `parallel` steps), figure out what is the API end point for accessing raw pipeline steps.  

[See full dev checklist](https://github.com/10000TB/state-replay/blob/master/DEV.md)

## Install

First, you need to build a plugin. Simply run 
```
mvn install
``` 
from repo root. Then, you will find a `hpi` file under `./target/` called state-replay.hpi, which you can use to install on your Jenkins.  
Then, go to your Jenkins server, navigate to **Manage Jenkins -> Manage Jenkins**. After that, you can see an **Advanced** tab in current page. Afterwards, there will be an **Upload Plugin** section if you click the **Advanced** tab. You can then upload the built `.hpi` file, and Jenkins will recognize it and install this plugin for you. Then you are good to go!

**One more way** to see how this plugin works, simply run(from current repo root):
```
mvn hpi:run
```
You will have a local Jenkins server up at `http://localhost:8080/jenkins/` with plugin installed by default.

## Examples
Some example usage would be like following once this plugin is finished. In addition, there will be a best-practice published for taking advantage of this plugin.  

0.  **statepoint syntax**  
You can always use pipeline syntax generator to find out how to make a step call like following. 
![syntaxx generator](https://github.com/10000TB/state-replay/blob/master/src/main/resources/imgs/syntax_generate.png)
But list here anyway for reference in case you just want it.  
```
statepoint stateMessage:"<State message>",
           isVolatile:<VOLATILE: boolean>,
           remoteOperation:<REMOTEOPERATION: boolean>,
           retry: <NUMBEROFRETRY: INT>
```

1.  **a simple example proving the concept.**
```
node("") {
    stage("preparation"){
        // env preparation steps.
    }
    statepoint "env preparation done."
    stage("Build"){
        // some build steps.
    }
    statepoint "Build done"
    stage("Push to artifactory"){
        // push to artifactory.
    }
    statepoint "Pushed to artifactory."
}
```
If any of the step failed, successful state, which was saved per build per pipeline, will be picked up when replay. For example, if "Push to artifactory" failed due to network issue, you can simply replay from "Build done" statepoint.  
  
2.  **using retry option to retry known fragile steps:**  
There are usually some fragile steps some where in pipeline, instead of using `retry` step, we provide a retry option to automatically replay all steps from last state point. Just so it looks neater and more beautiful in code.
```
node("") {
    .... // some pipeline steps
    stage("Build"){
        // some build steps.
    }
    statepoint "Build done"
    stage("Push to artifactory"){
        // push to artifactory.
    }
    statepoint retry: 3, stateMessage: 'Push to artifactory'
    ... // some more pipeline steps.
}
```
If due to fleakness in the stage of "Push to artifactory", this plugin will automatically replay steps after "Build done" before marking it as failure. It will proceed to following steps if any of the retry succeeds.

(todo:10000tb) add more examples.

## Contribute

1. Fork the project & clone locally.
2. Create an upstream remote and sync your local copy before you branch.
3. Branch for each separate piece of work.
4. Do the work, write <Strong>good commit messages</Strong>.
5. Push to your origin repository.
6. Create a new PR.

## Motivation
(For open sourced Jenkins distribution)
* Current workflow pipeline doesn't support replaying from checkpoint. 
* Heavy product often needs long-running pipeline on CI side, and replay from beginning everytime wastes time in repeating steps that already passed.
* Introducing such checkpoint plugin, help debugging/development effiency of developing workflow pipeline in groovy.

## Liscense

State-replay is under [MIT Liscense](https://github.com/10000TB/state-replay/blob/master/LICENSE), The terms of the license are as follows:

```
MIT License

Copyright (c) 2017 Xuehao(David)  Hu

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
