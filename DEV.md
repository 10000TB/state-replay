
## Dev ToDo list

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
