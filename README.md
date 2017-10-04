# state-replay

[![Build Status](https://travis-ci.org/10000TB/state-replay.svg?branch=master)](https://travis-ci.org/10000TB/state-replay) [![CircleCI](https://circleci.com/gh/10000TB/state-replay/tree/master.svg?style=svg)](https://circleci.com/gh/10000TB/state-replay/tree/master) [![Gitter chat](https://badges.gitter.im/state-replay/gitter.png)](https://gitter.im/state-replay/gitter)
  
  
  
![statereplay illustration](https://github.com/10000TB/state-replay/blob/master/src/main/resources/imgs/State-replay.png?raw=true)  
  
  
  
  

state-replay(<strong>WIP</strong>) helps you replay pipeline from predefined checkpoint(s).
  
  
  
[Install](#install)  
[Road Map](#road-map)  
[Current Development](#dev)  
[How to Contribute](#contribute)  
[Who do we need this plugin?](#motivation)  
[Liscense](#liscense)  

## Dev
- [ ] The `replay` will be first implemented in a simply way (For each `statepoint`, save variables name and values before, replay will reconstruct a temp workflow execution context, and workflow pipeline will start from there with those finished variables and values from last execution).
- [ ] Under the hoods, steps in workflow pipeline are parsed and run one by one in single thread(multiple threads for `parallel` steps), figure out what is the API end point for accessing raw pipeline steps.
- [X] Figure how workflow pipeline works, and what is the direction to go towards in investigation.
- [X] Implemented basic storage: under `<JOB_HOME>`, similar to `builds`, this plugin now have a root directory for each job called `states`. and a `<BUILD_NUMBER>` directory for each build. Then the checkpoints for each build will be captured and stored in `<JENKINS_HOME>/jobs/<JOB_NAME>/states/<BUILD_NUMBER>`. That being said, checkpoints will be saved in a very similar like builds, and will in most extend copy same features from builds(auto delete older builds, only save most recent builds, etc).
- [x] Step (`statepoint`), which can be used in Groovy pipeline.

## Road Map

<details>
           <summary>sr-1.0</summary>
           <p>Beta version: `statepoint` can be used to define checkpoint in Groovy pipeline. Such pipelines can replay from a checkpoint that is before the last failure point(s). (Note: If such pipelines have parallel steps, replay can happen from each corresponding checkpoint right before their last failure.)</p>
</details>
<details>
           <summary>sr-2.0</summary>
           <p>A reliable version with only 1.0 featues: Bug fixes to make 1.0 reliable.</p>
</details>
<details>
           <summary>sr-3.0</summary>
           <p>A complete version: replay can happen from predefined checkpoint.</p>
</details>

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

## Contribute

1. Fork the project & clone locally.
2. Create an upstream remote and sync your local copy before you branch.
3. Branch for each separate piece of work.
4. Do the work, write <Strong>good commit messages</Strong>, and read the CONTRIBUTING file if there is one.
5. Push to your origin repository.
6. Create a new PR.

## Contributors

* **David Hu** - *Initiator and initial work on `statepoint`* - [10000TB](https://github.com/10000TB)

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
