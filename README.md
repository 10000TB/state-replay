# state-replay
![statereplay illustration](https://github.com/10000TB/state-replay/blob/master/src/main/resources/imgs/State-replay.png?raw=true)

One great workflow pipeline to rule them all. This Jenkins plugin(<strong>WIP</strong>) will help you save the runtime state in workflow pipeline, and let you replay the pipeline from predefined checkpoint. For example, you can replay pipeline from the last failing point after you fix infra or product related failure(s). No need to waste hour(s) in replaying from beginning.

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
(TODO: David Hu ) add installation instructions.

## Contribute
(TODO: David Hu) add contribution guide.

## Liscense
