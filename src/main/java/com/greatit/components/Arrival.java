package com.greatit.components;

import com.greatit.enums.AnimalKind;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.ToString;
import lombok.AccessLevel;

import java.util.UUID;

import org.springframework.lang.Nullable;

@Getter
@Setter
@ToString
public class Arrival {
    @Setter(AccessLevel.NONE)
    private UUID id = UUID.randomUUID();
    private @NonNull String time;
    private @NonNull AnimalKind kind;
    private @Nullable Boolean curious;
    private Boolean examined = false; 

    /**
     * Here is when one of the animal goes to examination room...
     * Based on its Kind, business cased behaviour calculation happens
     * @param result - reference to the original Result entry in the memory
     * @param arrivals - reference to the original Arrivals object, filled by animals in the waiting queue
     */
    public void GoToExamination(Result result, Arrivals arrivals) {
        switch (this.getKind()) {
            case DOG:
                // 1. State is set to "examined", which decrease the counter of dogs, waiting in the room outside
                // 2. Dogs being examined by the vet start barking exactly once per examination
                // 3. When a dog barks its owner tells them to stop immediately after
                this.setExamined(true);
                result.barks++;
                result.verbalInterruptions++;

                // 1. Recalcutes the number of dogs in the waiting room, how many will bark back
                // 2. When a dog barks in the examination room, dogs outside barking immediately
                // 3. Owner tells them to stop immediately
                long cntDogsInWaitingRoom = arrivals.CountDogsInWaitingRoom();
                result.barks += cntDogsInWaitingRoom;
                result.verbalInterruptions += cntDogsInWaitingRoom;

                // 1. When a curious dog approaches a cat, the cat hisses at them once
                //    Based on business definition, definition whether dog is curious or not, is not mandatory
                // 2. There is no exact business definition, how many times a dog approaches a cat
                //    That's why it is calculated one time at the beginning
                if (this.curious != null && this.curious) {
                    result.hissis++;
                }
                break;
            case CAT:
                // 1. Cats being examined by the vet hiss at the vet exactly once per examination
                //    Dogs and cats in the waiting room cannot hear cats hissing in the examination room
                this.setExamined(true);    
                result.hissis++;
                
                break;
        }
    } 

    public boolean hasDOG() {
        return this.getKind().equals(AnimalKind.DOG);
    }

    public boolean isWaiting() {
        return !this.getExamined();
    }

    public boolean isDogsWaiting() {
        return hasDOG() && isWaiting();
    }
}
