package cmu.axis.model;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import cmu.axis.databean.ItemBean;

public class ItemDAO {
	private DatastoreService datastore   = DatastoreServiceFactory.getDatastoreService();
	private Key rootKey = KeyFactory.createKey("ToDoList", "root");
	private Query ascendingQuery = new Query("ToDoList", rootKey).addSort("position", Query.SortDirection.ASCENDING);
	
	public void addToTop(ItemBean item) throws DAOException {
		Transaction t = null;
		try {
			t = datastore.beginTransaction();
			
			// Get item at top of list
			List<ItemBean> items = runAscendingQuery();
			
			ItemBean topBean;
			if (items.size() == 0) {
				topBean = null;
			} else {
				topBean = items.get(0);
			}
			
			long newPos;
			if (topBean == null) {
				// List is empty...just add it with position = 1
				newPos = 1;
			} else {
				// Create the new item with position one less than the top bean's position
				newPos = topBean.getPosition() - 1;
			}

			item.setPosition(newPos);

			Entity e = new Entity("ToDoList", rootKey);
			e.setProperty("position", item.getPosition());
			e.setProperty("item", item.getItem());
			e.setProperty("userName", item.getUserName());
			
			// Create a new entity in the datastore.  Id will be automatically set.
			datastore.put(e);
			
			item.setId(e.getKey().getId());

			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			if (t != null && t.isActive()) t.rollback();
		}
	}
	
	public void addToBottom(ItemBean item) throws DAOException {
		Transaction t = datastore.beginTransaction();
		
		try {
			// Get item at top of list
			List<ItemBean> items = runAscendingQuery();
			
			ItemBean bottomBean;
			if (items.size() == 0) {
				bottomBean = null;
			} else {
				bottomBean = items.get(items.size()-1);
			}
			
			long newPos;
			if (bottomBean == null) {
				// List is empty...just add it with position = 1
				newPos = 1;
			} else {
				// Create the new item with position one more than the bottom bean's position
				newPos = bottomBean.getPosition() + 1;
			}

			item.setPosition(newPos);

			Entity e = new Entity("ToDoList", rootKey);
			e.setProperty("position", item.getPosition());
			e.setProperty("item", item.getItem());
			e.setProperty("userName", item.getUserName());
			
			// Create a new entity in the datastore.  Id will be automatically set.
			datastore.put(e);
			
			item.setId(e.getKey().getId());

			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			if (t.isActive()) t.rollback();
		}
	}
	
	public ItemBean[] getItems() throws DAOException {
		try {
			List<ItemBean> items = runAscendingQuery();
			return items.toArray(new ItemBean[items.size()]);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	public void delete(long id) throws DAOException {
		try {
			Key key = KeyFactory.createKey(rootKey, "ToDoList", id);
			datastore.delete(key);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	private List<ItemBean> runAscendingQuery() {
		List<Entity> entities = datastore.prepare(ascendingQuery).asList(FetchOptions.Builder.withLimit(100));
		List<ItemBean> items = new ArrayList<ItemBean>();
		for (Entity e : entities) {
			items.add(makeBean(e));
 		}
		return items;
	}
	
	private ItemBean makeBean(Entity e) {
		ItemBean bean = new ItemBean();
		bean.setId(e.getKey().getId());
		bean.setItem((String) e.getProperty("item"));
		bean.setPosition((Long) e.getProperty("position"));
		bean.setUserName((String) e.getProperty("userName"));
		return bean;
	}

}
