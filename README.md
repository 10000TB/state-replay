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

- [ ] Implement the "runtime state saving and replay"
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

To build a plugin, simply run 
```
mvn install
``` 
from repo root. Then, you will find a `hpi` file under `./target/` called state-replay.hpi, which you can use to install on your Jenkins.  
One more way to see how this plugin works, simply run:
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
