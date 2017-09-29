# state-replay
![statereplay illustration](https://github.com/10000TB/state-replay/blob/master/src/main/resources/imgs/State-replay.png?raw=true)

One great workflow pipeline to rule them all. This Jenkins plugin(<strong>WIP</strong>) will help you save the runtime state in workflow pipeline, and let you replay the pipeline from predefined checkpoint. No need to waste hour(s) in replaying from beginning.

## Motivation
>For open sourced Jenkins distribution.

1. Current workflow pipeline doesn't support replaying from checkpoint.
2. Heavy product often needs long-running pipeline on CI side, and replay from beginning everytime wastes time in repeating steps that already passed.
3. Introduce such checkpoint plugin, help debugging/development effiency of developing workflow pipeline in groovy.

## Current Status

Done          | TODO          | Happening     |
------------- | ------------- | ------------- |
1.Step (`statepoint`), which can be used in Groovy pipeline( but not doing anything, but printing in console)  |  1.Implement state saving, replaying  |  1.Figuring out how to implement the "runtime state saving and replay"

## Road Map

Release       | Description   | Delivery      |
------------- | ------------- | ------------- |
1.sr- 1.0 | Beta version: `statepoint` can be used to define checkpoint in Groovy pipeline. Such pipelines can replay from a checkpoint that is before the last failure point(s). (Note: If such pipelines have parallel steps, replay can happen from each corresponding checkpoint right before their last failure.) | END of Sept of 2017
2.sr-2.0 | A reliable version with only 1.0 featues: Bug fixes to make 1.0 reliable. | Feb 2018
3.sr-3.0 | A complete version: replay can happen from predefined checkpoint. | May 2018

## Install

To build a plugin, simply run `mvn install` from repo root. Then, you will find a `hpi` file under `./target/` called state-replay.hpi, which you can use to install on your Jenkins.

## Contribute

1. Fork the project & clone locally.
2. Create an upstream remote and sync your local copy before you branch.
3. Branch for each separate piece of work.
4. Do the work, write <Strong>good commit messages</Strong>, and read the CONTRIBUTING file if there is one.
> Simply run `mvn hpi:run` from GIT_ROOT to see how your change(s) behave on a Jenkins server.
5. Push to your origin repository.
6. Create a new PR.

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
