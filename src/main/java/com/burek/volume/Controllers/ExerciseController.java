package com.burek.volume.Controllers;

import com.burek.volume.Classes.Exercise;
import com.burek.volume.Classes.ExerciseSender;
import com.burek.volume.Classes.Plan;
import com.burek.volume.Repositories.ExerciseRepository;
import com.burek.volume.Repositories.PlanRepository;
import com.burek.volume.Requests.ExerciseCompletionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ExerciseController {
    private final ExerciseRepository exerciseRepository;
    private final PlanRepository planRepository;

    public ExerciseController(ExerciseRepository exerciseRepository, PlanRepository planRepository) {
        this.exerciseRepository = exerciseRepository;
        this.planRepository = planRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/perform_login")
    @ResponseBody
    public void xx() {
        System.out.println("QQQQQQQ");
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/exercise/{id}")
    @ResponseBody
    public String getExerciseById(@PathVariable("id") long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        return exercise.getName();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/exercises")
    @ResponseBody
    public List<ExerciseSender> getAllExercises() {
        List<Exercise> base = (List<Exercise>) exerciseRepository.findAll();
        return dataConverter(base);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/exercises/plan/{id}")
    @ResponseBody
    public List<ExerciseSender> getExercisesByPlanId(@PathVariable("id") long id) {
        List<Exercise> base = exerciseRepository.findByPlanId(id);
        return dataConverter(base);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/exercises/{id}")
    @ResponseBody
    public ExerciseSender getExerciseByPlanId(@PathVariable("id") long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow();
        ExerciseSender exerciseSender = new ExerciseSender();
        exerciseSender.setFails(exercise.getFails());
        exerciseSender.setPlanId(exercise.getPlan().getId());
        exerciseSender.setReps(exercise.getReps());
        exerciseSender.setWeight(exercise.getWeight());
        exerciseSender.setId(exercise.getId());
        exerciseSender.setName(exercise.getName());
        return exerciseSender;
    }

    List<ExerciseSender> dataConverter(List<Exercise> base){
        List<ExerciseSender> exercises = new ArrayList<>();

        for (Exercise exercise : base) {
            ExerciseSender exerciseSender = new ExerciseSender();
            exerciseSender.setFails(exercise.getFails());
            exerciseSender.setPlanId(exercise.getPlan().getId());
            exerciseSender.setReps(exercise.getReps());
            exerciseSender.setWeight(exercise.getWeight());
            exerciseSender.setId(exercise.getId());
            exerciseSender.setName(exercise.getName());
            exercises.add(exerciseSender);
        }

        return exercises;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/exercise/completion")
    public ResponseEntity postController(
            @RequestBody ExerciseCompletionRequest exerciseCompletionRequest) {
        Exercise exercise = exerciseRepository.findById(exerciseCompletionRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + exerciseCompletionRequest.getId()));

        Plan plan = planRepository.findById(exercise.getPlan().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + exercise.getPlan().getId()));

        if(exerciseCompletionRequest.isSuccess())
        {
            exercise.setReps(exercise.getReps()+exerciseCompletionRequest.getDifficulty());
            if(exercise.getReps()>10) {
                exercise.setWeight(howMuchWeight(exerciseCompletionRequest.getDifficulty(), plan.getUnits(), exercise.getWeight(), plan.getRound()));
                exercise.setReps(5);
            }
        }else{
            int load = 4*exercise.getReps();
            if(load-exerciseCompletionRequest.getLoad()<=exercise.getReps()){
                if(exercise.getFails()==2){
                    exercise.setFails(0);
                    exercise.setWeight(deload(exercise.getWeight(), plan.getRound()));
                }else{
                    exercise.setFails(exercise.getFails()+1);
                }
            }else{
                exercise.setWeight(deload(exercise.getWeight(), plan.getRound()));
            }
        }

        exerciseRepository.save(exercise);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private float howMuchWeight(int difficulty, String units, float weight, float round){
        if(Objects.equals(units, "kg")){
            if(difficulty>1 && round<=5){
                return weight + 5;
            }else{
                return weight + round;
            }
        }else{
            if(difficulty>1){
                return weight + 10;
            }else{
                return weight + round;
            }
        }
    }

    private float deload(float weight, float round){
        return round*(Math.round(((weight*9)/10)/round));
    }

}
