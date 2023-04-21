package com.project.agroworldapp.taskmanager.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.project.agroworldapp.db.FarmerDAO;
import com.project.agroworldapp.db.FarmerDatabase;
import com.project.agroworldapp.db.FarmerModel;

import java.util.List;

public class DatabaseRepository {
    static long maxIDCount;
    private final FarmerDAO dao;
    private final LiveData<List<FarmerModel>> farmerRoutines;

    public DatabaseRepository(Application application) {
        FarmerDatabase database = FarmerDatabase.getInstance(application);
        dao = database.taskDao();
        farmerRoutines = dao.getFarmerRoutines();
    }

    public long insert(FarmerModel model) {
        new InsertRoutineAsyncTask(dao).execute(model);
        return maxIDCount;
    }

    public void update(FarmerModel model) {
        new UpdateRoutineAsyncTask(dao).execute(model);
    }

    public void deleteAllCourses() {
        new DeleteAllCoursesAsyncTask(dao).execute();
    }

    public void delete(FarmerModel model) {
        new DeleteRoutineAsyncTask(dao).execute(model);
    }

    public LiveData<List<FarmerModel>> getFarmerRoutines() {
        return farmerRoutines;
    }

    private static class InsertRoutineAsyncTask extends AsyncTask<FarmerModel, Void, Long> {
        private final FarmerDAO dao;

        private InsertRoutineAsyncTask(FarmerDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Long doInBackground(FarmerModel... model) {
            // below line is use to insert our modal in dao.
            return dao.insert(model[0]);
        }

        @Override
        protected void onPostExecute(Long integer) {
            maxIDCount = integer;
        }
    }

    private static class UpdateRoutineAsyncTask extends AsyncTask<FarmerModel, Void, Void> {
        private final FarmerDAO dao;

        private UpdateRoutineAsyncTask(FarmerDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(FarmerModel... models) {
            dao.update(models[0]);
            return null;
        }
    }

    private static class DeleteRoutineAsyncTask extends AsyncTask<FarmerModel, Void, Void> {
        private final FarmerDAO dao;

        private DeleteRoutineAsyncTask(FarmerDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(FarmerModel... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void> {
        private final FarmerDAO dao;

        private DeleteAllCoursesAsyncTask(FarmerDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteRoutines();
            return null;
        }
    }
}
