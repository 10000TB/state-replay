# state-replay
One great workflow pipeline to rule them all. This Jenkins plugin(<strong>WIP</strong>) will help you save the runtime state in workflow pipeline, and let you replay the pipeline from predefined checkpoint. For example, you can replay pipeline from the last failing point after you fix infra or product related failure(s). No need to waste hour(s) in replaying from beginning.

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

## Motivation
>For open sourced Jenkins distribution.

TL;DR
1. Current workflow pipeline doesn't support replaying from checkpoint.
2. Heavy product often needs long-running pipeline on CI side, and replay from beginning everytime wastes time in repeating steps that already passed.
3. Introduce such checkpoint plugin, help debugging/development effiency of developing workflow pipeline in groovy.

The actual story or my testimonials of there should be such plugin:

When I was working on a long-running pipeline(it usually takes 6-8 hours to finish the pipeline) for my organization, it was extremely hard to replay the pipeline in a cost efficiently way if it fails after running for a few hours. For example, when the pipeline failed in the middle(say it failed after 3 hours of running), I then need to restart the pipeline from scratch and wait for 3 hours to reach last failure point after I resolve issue(s) (it could infra, pipeline syntax, or product code base). Imagine that the pipeline could fail anywhere in the middle, and you only 24 hours a day, then it becomes extremely hard to productionize the pipeline in a efficient way and in a short time period. At the same time, I attended 2017 Jenkins World Conference and was amazed by how much access you can have to Jenkins system through plugin, then I had the idea of writting plugin to solve the problem.

## Install
(TODO: David Hu ) add installation instructions.

## Contribute
(TODO: David Hu) add contribution guide.

## Liscense
