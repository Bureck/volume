package com.burek.volume.Controllers;

import com.burek.volume.Classes.Exercise;
import com.burek.volume.Classes.Plan;
import com.burek.volume.Repositories.ExerciseRepository;
import com.burek.volume.Repositories.PlanRepository;
import com.burek.volume.Requests.PlanCreationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlanController {
    private final PlanRepository planRepository;
    private final ExerciseRepository exerciseRepository;

    public PlanController(PlanRepository planRepository, ExerciseRepository exerciseRepository) {
        this.planRepository = planRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/plan/{id}")
    @ResponseBody
    public String getPlanById(@PathVariable("id") long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        return plan.getUnits();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/request")
    public ResponseEntity postController(
            @RequestBody PlanCreationRequest planCreationRequest) {
        float round = planCreationRequest.getRound();

        Plan plan = new Plan();
        plan.setRound(round);
        plan.setUnits(planCreationRequest.getUnits());
        planRepository.save(plan);

        if(planCreationRequest.getDeadliftWeight()!=0){
            Exercise exercise = setExercisePlan("Deadlift", planCreationRequest.getDeadliftWeight(), planCreationRequest.getDeadliftReps(), round);
            exercise.setPlan(plan);
            exerciseRepository.save(exercise);
        }

        if(planCreationRequest.getSquatWeight()!=0){
            Exercise exercise = setExercisePlan("Squat", planCreationRequest.getSquatWeight(), planCreationRequest.getSquatReps(), round);
            exercise.setPlan(plan);
            exerciseRepository.save(exercise);
        }

        if(planCreationRequest.getBenchWeight()!=0){
            Exercise exercise = setExercisePlan("Bench Press", planCreationRequest.getBenchWeight(), planCreationRequest.getBenchReps(), round);
            exercise.setPlan(plan);
            exerciseRepository.save(exercise);
        }

        if(planCreationRequest.getOhpWeight()!=0){
            Exercise exercise = setExercisePlan("Overhead Press", planCreationRequest.getOhpWeight(), planCreationRequest.getOhpReps(), round);
            exercise.setPlan(plan);
            exerciseRepository.save(exercise);
        }

        if(planCreationRequest.getRowWeight()!=0){
            Exercise exercise = setExercisePlan("Pendlay Row", planCreationRequest.getRowWeight(), planCreationRequest.getRowReps(), round);
            exercise.setPlan(plan);
            exerciseRepository.save(exercise);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Exercise setExercisePlan(String name, float w, int r, float round){
        Exercise exercise = new Exercise();

        double rm= 100*w/(100-(2.5*r));
        double Weight = rm - (20*rm)/100;
        Weight = (round*(Math.round(Weight/round)));
        float trainingWeight = (float)Weight;

        exercise.setFails(0);
        exercise.setName(name);
        exercise.setReps(5);
        exercise.setWeight(trainingWeight);

        return exercise;
    }
}
