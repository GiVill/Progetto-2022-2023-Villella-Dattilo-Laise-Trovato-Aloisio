package com.example.vintedandroid.model;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.vintedandroid.model.dao.BasicInsertionDao;
import com.example.vintedandroid.model.dao.BasicInsertionDao_Impl;
import com.example.vintedandroid.model.dao.BuyingOfferDao;
import com.example.vintedandroid.model.dao.BuyingOfferDao_Impl;
import com.example.vintedandroid.model.dao.OrderDao;
import com.example.vintedandroid.model.dao.OrderDao_Impl;
import com.example.vintedandroid.model.dao.PaymentDao;
import com.example.vintedandroid.model.dao.PaymentDao_Impl;
import com.example.vintedandroid.model.dao.UserDao;
import com.example.vintedandroid.model.dao.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile PaymentDao _paymentDao;

  private volatile OrderDao _orderDao;

  private volatile BuyingOfferDao _buyingOfferDao;

  private volatile BasicInsertionDao _basicInsertionDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `userDto` (`id` INTEGER NOT NULL, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `email` TEXT NOT NULL, `birthDate` TEXT NOT NULL, `gender` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `orderDto` (`id` INTEGER NOT NULL, `date` TEXT NOT NULL, `paymentid` INTEGER NOT NULL, `number` INTEGER NOT NULL, `inserionId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `paymentDto` (`id` INTEGER, `paymentMethod` TEXT, `status` TEXT, `userId` INTEGER, `orderId` INTEGER, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `buyingOfferDto` (`id` INTEGER NOT NULL, `price` REAL NOT NULL, `status` TEXT NOT NULL, `insertionId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `basicInsertionDto` (`id` INTEGER NOT NULL, `title` TEXT NOT NULL, `price` INTEGER NOT NULL, `description` TEXT NOT NULL, `condition` TEXT NOT NULL, `creationDate` TEXT NOT NULL, `endDate` TEXT NOT NULL, `imagePath` TEXT NOT NULL, `userId` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9f3ccec3a8e478d13a2ac8ce42848089')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `userDto`");
        _db.execSQL("DROP TABLE IF EXISTS `orderDto`");
        _db.execSQL("DROP TABLE IF EXISTS `paymentDto`");
        _db.execSQL("DROP TABLE IF EXISTS `buyingOfferDto`");
        _db.execSQL("DROP TABLE IF EXISTS `basicInsertionDto`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUserDto = new HashMap<String, TableInfo.Column>(6);
        _columnsUserDto.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDto.put("firstName", new TableInfo.Column("firstName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDto.put("lastName", new TableInfo.Column("lastName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDto.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDto.put("birthDate", new TableInfo.Column("birthDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDto.put("gender", new TableInfo.Column("gender", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserDto = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserDto = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserDto = new TableInfo("userDto", _columnsUserDto, _foreignKeysUserDto, _indicesUserDto);
        final TableInfo _existingUserDto = TableInfo.read(_db, "userDto");
        if (! _infoUserDto.equals(_existingUserDto)) {
          return new RoomOpenHelper.ValidationResult(false, "userDto(com.example.vintedandroid.model.dto.UserDto).\n"
                  + " Expected:\n" + _infoUserDto + "\n"
                  + " Found:\n" + _existingUserDto);
        }
        final HashMap<String, TableInfo.Column> _columnsOrderDto = new HashMap<String, TableInfo.Column>(6);
        _columnsOrderDto.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrderDto.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrderDto.put("paymentid", new TableInfo.Column("paymentid", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrderDto.put("number", new TableInfo.Column("number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrderDto.put("inserionId", new TableInfo.Column("inserionId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrderDto.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOrderDto = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOrderDto = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOrderDto = new TableInfo("orderDto", _columnsOrderDto, _foreignKeysOrderDto, _indicesOrderDto);
        final TableInfo _existingOrderDto = TableInfo.read(_db, "orderDto");
        if (! _infoOrderDto.equals(_existingOrderDto)) {
          return new RoomOpenHelper.ValidationResult(false, "orderDto(com.example.vintedandroid.model.dto.OrderDto).\n"
                  + " Expected:\n" + _infoOrderDto + "\n"
                  + " Found:\n" + _existingOrderDto);
        }
        final HashMap<String, TableInfo.Column> _columnsPaymentDto = new HashMap<String, TableInfo.Column>(5);
        _columnsPaymentDto.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentDto.put("paymentMethod", new TableInfo.Column("paymentMethod", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentDto.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentDto.put("userId", new TableInfo.Column("userId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentDto.put("orderId", new TableInfo.Column("orderId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPaymentDto = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPaymentDto = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPaymentDto = new TableInfo("paymentDto", _columnsPaymentDto, _foreignKeysPaymentDto, _indicesPaymentDto);
        final TableInfo _existingPaymentDto = TableInfo.read(_db, "paymentDto");
        if (! _infoPaymentDto.equals(_existingPaymentDto)) {
          return new RoomOpenHelper.ValidationResult(false, "paymentDto(com.example.vintedandroid.model.dto.PaymentDto).\n"
                  + " Expected:\n" + _infoPaymentDto + "\n"
                  + " Found:\n" + _existingPaymentDto);
        }
        final HashMap<String, TableInfo.Column> _columnsBuyingOfferDto = new HashMap<String, TableInfo.Column>(5);
        _columnsBuyingOfferDto.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBuyingOfferDto.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBuyingOfferDto.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBuyingOfferDto.put("insertionId", new TableInfo.Column("insertionId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBuyingOfferDto.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBuyingOfferDto = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBuyingOfferDto = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBuyingOfferDto = new TableInfo("buyingOfferDto", _columnsBuyingOfferDto, _foreignKeysBuyingOfferDto, _indicesBuyingOfferDto);
        final TableInfo _existingBuyingOfferDto = TableInfo.read(_db, "buyingOfferDto");
        if (! _infoBuyingOfferDto.equals(_existingBuyingOfferDto)) {
          return new RoomOpenHelper.ValidationResult(false, "buyingOfferDto(com.example.vintedandroid.model.dto.BuyingOfferDto).\n"
                  + " Expected:\n" + _infoBuyingOfferDto + "\n"
                  + " Found:\n" + _existingBuyingOfferDto);
        }
        final HashMap<String, TableInfo.Column> _columnsBasicInsertionDto = new HashMap<String, TableInfo.Column>(9);
        _columnsBasicInsertionDto.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBasicInsertionDto.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBasicInsertionDto.put("price", new TableInfo.Column("price", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBasicInsertionDto.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBasicInsertionDto.put("condition", new TableInfo.Column("condition", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBasicInsertionDto.put("creationDate", new TableInfo.Column("creationDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBasicInsertionDto.put("endDate", new TableInfo.Column("endDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBasicInsertionDto.put("imagePath", new TableInfo.Column("imagePath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBasicInsertionDto.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBasicInsertionDto = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBasicInsertionDto = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBasicInsertionDto = new TableInfo("basicInsertionDto", _columnsBasicInsertionDto, _foreignKeysBasicInsertionDto, _indicesBasicInsertionDto);
        final TableInfo _existingBasicInsertionDto = TableInfo.read(_db, "basicInsertionDto");
        if (! _infoBasicInsertionDto.equals(_existingBasicInsertionDto)) {
          return new RoomOpenHelper.ValidationResult(false, "basicInsertionDto(com.example.vintedandroid.model.dto.BasicInsertionDto).\n"
                  + " Expected:\n" + _infoBasicInsertionDto + "\n"
                  + " Found:\n" + _existingBasicInsertionDto);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9f3ccec3a8e478d13a2ac8ce42848089", "d6471bf06e4e856262e1cc8195a035d0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "userDto","orderDto","paymentDto","buyingOfferDto","basicInsertionDto");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `userDto`");
      _db.execSQL("DELETE FROM `orderDto`");
      _db.execSQL("DELETE FROM `paymentDto`");
      _db.execSQL("DELETE FROM `buyingOfferDto`");
      _db.execSQL("DELETE FROM `basicInsertionDto`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PaymentDao.class, PaymentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OrderDao.class, OrderDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BuyingOfferDao.class, BuyingOfferDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BasicInsertionDao.class, BasicInsertionDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public PaymentDao paymentDao() {
    if (_paymentDao != null) {
      return _paymentDao;
    } else {
      synchronized(this) {
        if(_paymentDao == null) {
          _paymentDao = new PaymentDao_Impl(this);
        }
        return _paymentDao;
      }
    }
  }

  @Override
  public OrderDao orderDao() {
    if (_orderDao != null) {
      return _orderDao;
    } else {
      synchronized(this) {
        if(_orderDao == null) {
          _orderDao = new OrderDao_Impl(this);
        }
        return _orderDao;
      }
    }
  }

  @Override
  public BuyingOfferDao buyingOfferDao() {
    if (_buyingOfferDao != null) {
      return _buyingOfferDao;
    } else {
      synchronized(this) {
        if(_buyingOfferDao == null) {
          _buyingOfferDao = new BuyingOfferDao_Impl(this);
        }
        return _buyingOfferDao;
      }
    }
  }

  @Override
  public BasicInsertionDao basicInsertionDao() {
    if (_basicInsertionDao != null) {
      return _basicInsertionDao;
    } else {
      synchronized(this) {
        if(_basicInsertionDao == null) {
          _basicInsertionDao = new BasicInsertionDao_Impl(this);
        }
        return _basicInsertionDao;
      }
    }
  }
}
